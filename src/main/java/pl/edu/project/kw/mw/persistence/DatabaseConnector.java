package pl.edu.project.kw.mw.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.project.kw.mw.model.School;
import pl.edu.project.kw.mw.model.SchoolClass;
import pl.edu.project.kw.mw.model.Student;

import java.util.List;

public class DatabaseConnector {

    protected static DatabaseConnector instance = null;
    Session session;

    protected DatabaseConnector() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public void teardown() {
        session.close();
        HibernateUtil.shutdown();
        instance = null;
    }

    public Iterable<School> getSchools() {
        String hql = "FROM School";
        Query query = session.createQuery(hql);
        List schools = query.list();

        return schools;
    }


    public Iterable<Student> getStudent() {
        String hql = "FROM Student";
        Query query = session.createQuery(hql);
        List students = query.list();

        return students;
    }

    public void addSchool(School school) {
        Transaction transaction = session.beginTransaction();
        session.save(school);
        transaction.commit();
    }

    public void deleteSchool(String schoolId) {
        String hql = "FROM School S WHERE S.id=" + schoolId;
        Query query = session.createQuery(hql);
        List<School> results = query.list();
        Transaction transaction = session.beginTransaction();
        for (School s : results) {
            session.delete(s);
        }
        transaction.commit();
    }

    public Iterable<SchoolClass> getSchoolClasses() {
        String hql = "FROM SchoolClass";
        Query query = session.createQuery(hql);
        List schoolClasses = query.list();

        return schoolClasses;
    }

    public void addSchoolClass(SchoolClass schoolClass, String schoolId) {
        String hql = "FROM School S WHERE S.id=" + schoolId;
        Query query = session.createQuery(hql);
        List<School> results = query.list();
        Transaction transaction = session.beginTransaction();
        if (results.size() == 0) {
            session.save(schoolClass);
        } else {
            School school = results.get(0);
            school.addClass(schoolClass);
            session.save(school);
        }
        transaction.commit();
    }

    public void deleteSchoolClass(String schoolClassId) {
        String hql = "FROM SchoolClass S WHERE S.id=" + schoolClassId;
        Query query = session.createQuery(hql);
        List<SchoolClass> results = query.list();
        Transaction transaction = session.beginTransaction();
        for (SchoolClass s : results) {
            session.delete(s);
        }
        transaction.commit();
    }


    public void addStudent(Student student) {
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
    }

    public void deleteStudent(String studentId) {
        String hql = "FROM Student S WHERE S.id=" + studentId;
        Query query = session.createQuery(hql);
        List<Student> results = query.list();
        Transaction transaction = session.beginTransaction();
        for (Student s : results) {
            session.delete(s);
        }
        transaction.commit();
    }


}
