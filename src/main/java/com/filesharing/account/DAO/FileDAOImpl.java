package com.filesharing.account.DAO;

import com.filesharing.account.model.File;
import com.filesharing.account.model.User;
import com.filesharing.account.service.UserService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.SourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.lang.Math.toIntExact;

@Repository
public class FileDAOImpl implements FileDAO {

    @Override
    public void addFile(File p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("File saved successfully,File Details="+p);
    }

    @Override
    public void updateFile(File p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("File updated successfully,  Details="+p);
    }

    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(FileDAOImpl.class); // DELETE

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<File> listFiles() {
        Session session = this.sessionFactory.getCurrentSession();
        List<File> fileList = session.createQuery("from File").list();
        for(File p : fileList){
            logger.info("File List::"+p);
        }
        return fileList;
    }



    @Override
    public File getFileById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        File p = (File) session.load(File.class, new Integer(id));
        logger.info("Person loaded successfully, Person details="+p);
        return p;
    }
    @Override
    public File getFileByPath(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        File p = (File) session.load(File.class, new String(id));
        logger.info("Person loaded successfully, Person details="+p);
        return p;
    }

    @Override
    public List<File> listFilesOfUser(int user_req_id) {
        Session session = this.sessionFactory.getCurrentSession();


        Query query = session.createQuery("FROM File WHERE file_owner=:user_req_id");
        query.setParameter("user_req_id", user_req_id);

        List<File> fileList = query.list();
        return fileList;
    }

    @Override
    public void removeFile(int id,int user_req_id) {
        Session session = this.sessionFactory.getCurrentSession();


        Query query = session.createQuery("FROM File WHERE id=:id");
        query.setParameter("id", id);
        File tmpFile = (File) query.uniqueResult();

        if(user_req_id!=tmpFile.getFile_owner()){
            return;
        }

        java.io.File file = new java.io.File(tmpFile.getFile_path())  ;
        if(file.exists()) file.delete();
        else{
            return;
        }



        File p = (File) session.load(File.class, new String(tmpFile.getFile_path()));
        if(null != p){
            session.delete(p);
        }
        logger.info("Person deleted successfully, person details="+p);
    }
}
