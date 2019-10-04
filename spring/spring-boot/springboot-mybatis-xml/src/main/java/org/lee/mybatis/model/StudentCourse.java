package org.lee.mybatis.model;

import com.alibaba.fastjson.JSONObject;
import org.ostenant.springboot.learning.examples.mybatis.utils.JSONAttrGetter;

import java.io.Serializable;
import java.util.Objects;

public class StudentCourse implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private Integer studentId;

    /**
     *
     */
    private Integer courseId;

    /**
     *
     */
    private Double score;

    public Integer getId() {
        return id;
    }

    public StudentCourse withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public StudentCourse withStudentId(Integer studentId) {
        this.setStudentId(studentId);
        return this;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public StudentCourse withCourseId(Integer courseId) {
        this.setCourseId(courseId);
        return this;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getScore() {
        return score;
    }

    public StudentCourse withScore(Double score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", courseId=").append(courseId);
        sb.append(", score=").append(score);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StudentCourse other = (StudentCourse) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, courseId, score);
    }

    public static StudentCourse fromJson(JSONObject fromJsonObj) {
        if (fromJsonObj == null || fromJsonObj.isEmpty()) {
            return null;
        }
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setId(JSONAttrGetter.getInteger(fromJsonObj, StudentCourseKey.ID));
        studentCourse.setStudentId(JSONAttrGetter.getInteger(fromJsonObj, StudentCourseKey.STUDENT_ID));
        studentCourse.setCourseId(JSONAttrGetter.getInteger(fromJsonObj, StudentCourseKey.COURSE_ID));
        studentCourse.setScore(JSONAttrGetter.getDouble(fromJsonObj, StudentCourseKey.SCORE));
        return studentCourse;
    }

    public JSONObject toJson() {
        JSONObject toJsonObj = new JSONObject();
        toJsonObj.put(StudentCourseKey.ID, id);
        toJsonObj.put(StudentCourseKey.STUDENT_ID, studentId);
        toJsonObj.put(StudentCourseKey.COURSE_ID, courseId);
        toJsonObj.put(StudentCourseKey.SCORE, score);
        return toJsonObj;
    }

    public static final class StudentCourseKey {
        /**
         *
         */
        public static final String ID = "id";

        /**
         *
         */
        public static final String STUDENT_ID = "student_id";

        /**
         *
         */
        public static final String COURSE_ID = "course_id";

        /**
         *
         */
        public static final String SCORE = "score";
    }
}
