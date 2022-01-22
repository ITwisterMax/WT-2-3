package by.application.client.dao;

import by.application.client.entity.student.Student;
import by.application.client.entity.user.User;
import java.util.List;

/**
 * Student data access object api
 */
public interface StudentClientDAO
{
    /**
     * Login user
     *
     * @param user user instance
     *
     * @return User
     */
    User login(User user);

    /**
     * Register new user
     *
     * @param user user instance
     *
     * @return User
     */
    User register(User user);

    /**
     * Get all students
     *
     * @return List<Student>
     */
    List<Student> getAll();

    /**
     * Get student by id
     *
     * @param id student id
     *
     * @return Student
     */
    Student get(int id);

    /**
     *  Edit student
     *
     * @param newValue new student instance
     *
     * @return boolean
     */
    boolean edit(Student newValue);

    /**
     * Create new student
     *
     * @param newValue student instance
     *
     * @return boolean
     */
    boolean create(Student newValue);
}
