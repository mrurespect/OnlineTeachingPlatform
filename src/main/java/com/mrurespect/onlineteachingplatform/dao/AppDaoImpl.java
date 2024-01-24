package com.mrurespect.onlineteachingplatform.dao;

import com.mrurespect.onlineteachingplatform.entity.Course;
import com.mrurespect.onlineteachingplatform.entity.Instructor;
import com.mrurespect.onlineteachingplatform.entity.InstructorDetail;
import com.mrurespect.onlineteachingplatform.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{
    private final EntityManager entityManager ;
    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor); // it also save the details object because of cascade all
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id); // it also retrive th eInstructor details object
        //because of the default behavior of @OnetoOne fetch type is eager
    }

    //@Override
    //@Transactional
    //public void deleteInstructorById(int id) {
       // Instructor instructor =entityManager.find(Instructor.class,id);
      //  entityManager.remove(instructor); //it also delete instructor details because of cascade ALL
    //}

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id); //it also gonna retrieve the instructor object
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail =entityManager.find(InstructorDetail.class,id);
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail); // it delete also the assossiated instructor
    }

    @Override
    public List<Course> findCOursesByInstructorId(int Id){
        TypedQuery<Course> query =entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data",Id);
        List<Course> courses =query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
            TypedQuery<Instructor> query =entityManager.createQuery("" +
                    "select i from Instructor i "+
                    "join fetch i.courses "+
                    "join fetch i.instructorDetail "+  //
                    "where i.id =:data",Instructor.class);
            query.setParameter("data",id);
            Instructor instructor =query.getSingleResult();
            return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }




    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor =entityManager.find(Instructor.class,id);
        List<Course> courses =instructor.getCourses();

        for (Course tempCourse :courses){
            tempCourse.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseByID(int id) {

        //retrieve the course
        Course course =entityManager.find(Course.class,id);
        // remove the course
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course); //will save the course and the assotiated reviews thanks to Cascade.All
    }

    @Override
    public Course findCourseAndReviewsByCourseID(int id) {
        TypedQuery<Course> query=entityManager.createQuery(
                     "select c from Course c "+
                        "join fetch c.reviews "+
                        "where c.id=:data",Course.class
                );
        query.setParameter("data",id);

        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "+
                "join fetch c.students "+
                "where c.id=:data",Course.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentID(int id) {
        TypedQuery<Student> query =entityManager.createQuery(
                "select s from Student s " +
                "join fetch s.courses " +
                "where s.id=:data",Student.class);
        query.setParameter("data",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentByID(int id) {
        Student student =entityManager.find(Student.class,id);
        entityManager.remove(student);
    }

}
