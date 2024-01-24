package com.mrurespect.onlineteachingplatform;

import com.mrurespect.onlineteachingplatform.dao.AppDao;
import com.mrurespect.onlineteachingplatform.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OnlineTeachingPlatform {

    public static void main(String[] args) {
        SpringApplication.run(OnlineTeachingPlatform.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) {
        return runner -> {
            //createInstructor(appDao);
            //findInstructor(appDao);

            //deleteInstructor(appDao);
            //findInstructorDetail(appDao);

           //deleteInstructorDetail( appDao);
            
            //createInstructorWithCourse(appDao);
            //findInstructorWithCourses(appDao);
            //findCoursesForInstructor(appDao);
            //findInstructorWithCoursesJoinFetch(appDao);

            //updateInstructor(appDao);
            //updateCourse(appDao);
            // deleteInstructor(appDao);

            //deleteCourseByID(appDao);

            //createCourseAndReviews(appDao);
            //retrieveCoursesAndReviews(appDao);

            //deleteCourseAndReviews(appDao);
            
            //createCourseAndStudents(appDao);
            //findCourseAndStudents(appDao);
            //findStudentsAndCourses(appDao);
            //addMoreCoursesForStudent(appDao);
            //deleteCourse(appDao);
            //deleteStudent(appDao);
        };
    }

    private void deleteStudent(AppDao appDao) {
        int id =1 ;
        System.out.println("deleting student ...");
        appDao.deleteStudentByID(id);
        System.out.println("done");

    }

    private void deleteCourse(AppDao appDao) {

        System.out.println("deleting a course ...");
        appDao.deleteCourseByID(10);
        System.out.println("done");
    }

    private void addMoreCoursesForStudent(AppDao appDao) {
        int id =1 ;
        System.out.println("finding student ... id="+id);
        Student student =appDao.findStudentAndCoursesByStudentID(id);
        System.out.println("updating : adding courses to student ...");
        student.addCourse(new Course("Vue.js"));
        student.addCourse(new Course("Quick.js")); //it also add the courses due to CASCADE.merge
        appDao.update(student);
        System.out.println("done");
    }

    private void findStudentsAndCourses(AppDao appDao) {
        int id =1 ;
        Student student=appDao.findStudentAndCoursesByStudentID(id);
        System.out.println("finding student ... id="+id);
        System.out.println("student ="+student);
        System.out.println("student courses="+student.getCourses());
    }

    private void findCourseAndStudents(AppDao appDao) {
        int id=8;
        System.out.println("finding course ... id="+id);
        Course course =appDao.findCourseAndStudentByCourseId(id);
        System.out.println("course :"+course);
        System.out.println("course students :"+course.getStudents());

    }

    private void createCourseAndStudents(AppDao appDao) {
        Course course =new Course("machine learning");
        course.addStudent(new Student("mohamed","saad","gmail.ma"));
        course.addStudent(new Student("ismail","lavida","hotmail.ma"));
        appDao.save(course);
    }

    private void deleteCourseAndReviews(AppDao appDao) {
        int id=7 ;
        System.out.println("removing course ... id"+id);
        appDao.deleteCourseByID(id); //already exist ; delete also the reviews due to Cascade all
        System.out.println("done");
    }

    private void retrieveCoursesAndReviews(AppDao appDao) {
        int id=7;
        System.out.println("finding course ... id="+id);
        Course course=appDao.findCourseAndReviewsByCourseID(id);
        System.out.println("course :"+course);
        System.out.println("its reviews :"+course.getReviews());
    }

    private void createCourseAndReviews(AppDao appDao) {
        System.out.println("creating course ...");
        Course course =new Course("java EE");
        System.out.println("creating reviews for the course ...");
        Review review1 =new Review("this course is amazing !!!");
        Review review2 =new Review("amazing course !!!");
        course.addReview(review1);
        course.addReview(review2);
        appDao.save(course);
        System.out.println("Done");
    }

    private void deleteCourseByID(AppDao appDao) {
        int id =3 ;
        System.out.println("deleting course ... id="+id);
        appDao.deleteCourseByID(id);
        System.out.println("done");
    }

    private void updateCourse(AppDao appDao) {
        int id=2;
        System.out.println("finding course id="+id);
        Course course =appDao.findCourseById(id);
        course.setTitle("Enjoy .Net framework");
        System.out.println("updating course id="+id);
        appDao.update(course);
        System.out.println("done");
    }

    private void updateInstructor(AppDao appDao) {
        int id=7 ;
        System.out.println("finding instructor id ="+id);
        Instructor instructor=appDao.findInstructorById(id);
        System.out.println("updating instructor id ="+id);
        instructor.setLastName("john");
        appDao.update(instructor);
        System.out.println("done");
    }

    private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
        int id=7;
        System.out.println("finding instructor by id");
        Instructor instructor=appDao.findInstructorByIdJoinFetch(id); //find instructor and courses because of join fetch
        System.out.println("instructor :"+instructor);
        System.out.println("courses :"+instructor.getCourses());

    }

    private void findCoursesForInstructor(AppDao appDao) {
        int id =7 ;
        Instructor instructor=appDao.findInstructorById(id);
        System.out.println("instructor ="+instructor);
        System.out.println("find courses for Instructor with Id="+id);
        List<Course> courses =appDao.findCOursesByInstructorId(id);
        instructor.setCourses(courses);
        System.out.println("courses :"+instructor.getCourses());
    }

    private void findInstructorWithCourses(AppDao appDao) {
        int id=7;
        System.out.println("finding Instructor with Id="+id);
        Instructor instructor=appDao.findInstructorById(id);
        System.out.println("instructor = "+instructor);
        System.out.println("assotiated courses = "+instructor.getCourses());

        System.out.println("done");
    }

    private void createInstructorWithCourse(AppDao appDao) {
        Instructor instructor =new Instructor("samir","alpha","alpha@gmail.com");
        InstructorDetail instructorDetail =new InstructorDetail("none.com","swiming");
        instructor.setInstructorDetail(instructorDetail);

        //creating some courses instance :
        Course course1=new Course("spring");
        Course course2=new Course("react");
        instructor.addCourse(course1);
        instructor.addCourse(course2);

        //we will also save courses because of CascadeType.PERSIST
        System.out.println("saving instructor ...");
        appDao.save(instructor);
        System.out.println(instructor);
        System.out.println("courses: "+instructor.getCourses());
        System.out.println("done !");
    }

    private void deleteInstructorDetail(AppDao appDao) {
        int id=1;
        appDao.deleteInstructorDetailById(id);
    }

    private void findInstructorDetail(AppDao appDao) {
        int id =5 ;
        System.out.println("finding instructor detail ... id="+id);
        InstructorDetail instructorDetail=appDao.findInstructorDetailById(id);
        System.out.println("instructor detail "+instructorDetail);
        System.out.println("the assotiated instructor  "+instructorDetail.getInstructor());
    }

    private void deleteInstructor(AppDao appDao) {
        int id =1;
        System.out.println("deleting instructor ... id="+id);
        appDao.deleteInstructorById(id);
        System.out.println("done");
    }

    private void findInstructor(AppDao appDao) {
        int id =5 ;
        System.out.println("finding instructor .. ");
        Instructor instructor =appDao.findInstructorById(id);
        System.out.println("instructor :"+instructor.toString());
        System.out.println("the associated instructor details :"+instructor.getInstructorDetail().toString());

    }

    private void createInstructor(AppDao appDao) {
        Instructor instructor =new Instructor("mohamed","saad","email@gmail.com");
        InstructorDetail instructorDetail =new InstructorDetail("youtube.com","teaching");
        instructor.setInstructorDetail(instructorDetail);
        System.out.println("saving instructor "+instructor);
        appDao.save(instructor);
        System.out.println("done !");
    }
}
