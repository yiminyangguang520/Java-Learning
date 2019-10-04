package org.lee.mybatis.model;

import com.alibaba.fastjson.JSONObject;
import org.ostenant.springboot.learning.examples.mybatis.utils.JSONAttrGetter;

import java.io.Serializable;
import java.util.Objects;

public class Course implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 课程时间
     */
    private Double lessonPeriod;

    /**
     * 课程分数
     */
    private Double score;

    public Integer getId() {
        return id;
    }

    public Course withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Course withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getLessonPeriod() {
        return lessonPeriod;
    }

    public Course withLessonPeriod(Double lessonPeriod) {
        this.setLessonPeriod(lessonPeriod);
        return this;
    }

    public void setLessonPeriod(Double lessonPeriod) {
        this.lessonPeriod = lessonPeriod;
    }

    public Double getScore() {
        return score;
    }

    public Course withScore(Double score) {
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
        sb.append(", name=").append(name);
        sb.append(", lessonPeriod=").append(lessonPeriod);
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
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLessonPeriod() == null ? other.getLessonPeriod() == null : this.getLessonPeriod().equals(other.getLessonPeriod()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lessonPeriod, score);
    }

    public static Course fromJson(JSONObject fromJsonObj) {
        if (fromJsonObj == null || fromJsonObj.isEmpty()) {
            return null;
        }
        Course course = new Course();
        course.setId(JSONAttrGetter.getInteger(fromJsonObj, CourseKey.ID));
        course.setName(JSONAttrGetter.getString(fromJsonObj, CourseKey.NAME));
        course.setLessonPeriod(JSONAttrGetter.getDouble(fromJsonObj, CourseKey.LESSON_PERIOD));
        course.setScore(JSONAttrGetter.getDouble(fromJsonObj, CourseKey.SCORE));
        return course;
    }

    public JSONObject toJson() {
        JSONObject toJsonObj = new JSONObject();
        toJsonObj.put(CourseKey.ID, id);
        toJsonObj.put(CourseKey.NAME, name);
        toJsonObj.put(CourseKey.LESSON_PERIOD, lessonPeriod);
        toJsonObj.put(CourseKey.SCORE, score);
        return toJsonObj;
    }

    public static final class CourseKey {
        /**
         *
         */
        public static final String ID = "id";

        /**
         *
         */
        public static final String NAME = "name";

        /**
         *
         */
        public static final String LESSON_PERIOD = "lesson_period";

        /**
         *
         */
        public static final String SCORE = "score";
    }
}
