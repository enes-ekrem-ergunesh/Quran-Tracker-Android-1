package com.example.quran_tracker_1.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChapterDao {
    @Query("SELECT * FROM Chapter")
    List<Chapter> getAll();

    @Query("SELECT isCompleted FROM Chapter WHERE Chapter.ID=(:chapterID)")
    boolean isCompleted(int chapterID);

    @Query("SELECT * FROM Chapter WHERE Chapter.ID=(:chapterID)")
    Chapter getByID(int chapterID);

    @Query("SELECT * FROM Chapter WHERE Chapter.Name=(:chapterName)")
    Chapter getByName(String chapterName);

    @Query("SELECT * FROM Chapter WHERE Chapter.Name=(:chapterName) AND Chapter.Number=(:chapterNumber)")
    Chapter getByNameAndNumber(String chapterName, int chapterNumber);

    @Query("SELECT * FROM Chapter WHERE chapter.recitationID=(:recitationID)")
    List<Chapter> getByRecitationID(int recitationID);

    @Query("SELECT * FROM Chapter WHERE Chapter.dateModified=(:dateModified)")
    Chapter getByDateModified(long dateModified);

    @Query("UPDATE Chapter SET isCompleted = (:isCompleted) WHERE ID = (:chapterID)")
    void setCompleted(int chapterID, boolean isCompleted);

    @Query("UPDATE Chapter SET isCompleted = (:isCompleted) WHERE RecitationID = (:recitationID)")
    void setCompletedByRecitationId(int recitationID, boolean isCompleted);

    @Query("SELECT * FROM Chapter WHERE isCompleted=0 AND RecitationID=(:recitationID)")
    List<Chapter> inCompletedChapters(int recitationID);

    @Insert
    void insert(Chapter chapter);

    @Query("INSERT INTO Page(number, isCompleted, chapterID)" +
            "VALUES" +
            "(1, (:isCompleted), (:chapterID))," +
            "(2, (:isCompleted), (:chapterID))," +
            "(3, (:isCompleted), (:chapterID))," +
            "(4, (:isCompleted), (:chapterID))," +
            "(5, (:isCompleted), (:chapterID))," +
            "(6, (:isCompleted), (:chapterID))," +
            "(7, (:isCompleted), (:chapterID))," +
            "(8, (:isCompleted), (:chapterID))," +
            "(9, (:isCompleted), (:chapterID))," +
            "(10, (:isCompleted), (:chapterID))," +
            "(11, (:isCompleted), (:chapterID))," +
            "(12, (:isCompleted), (:chapterID))," +
            "(13, (:isCompleted), (:chapterID))," +
            "(14, (:isCompleted), (:chapterID))," +
            "(15, (:isCompleted), (:chapterID))," +
            "(16, (:isCompleted), (:chapterID))," +
            "(17, (:isCompleted), (:chapterID))," +
            "(18, (:isCompleted), (:chapterID))," +
            "(19, (:isCompleted), (:chapterID))," +
            "(20, (:isCompleted), (:chapterID))" +
            "")
    void insertPagesOfChapter20(int chapterID, boolean isCompleted);

    @Query("INSERT INTO Page(Number, isCompleted, ChapterID)" +
            "VALUES" +
            "(1, (:isCompleted), (:chapterID))," +
            "(2, (:isCompleted), (:chapterID))," +
            "(3, (:isCompleted), (:chapterID))," +
            "(4, (:isCompleted), (:chapterID))," +
            "(5, (:isCompleted), (:chapterID))," +
            "(6, (:isCompleted), (:chapterID))," +
            "(7, (:isCompleted), (:chapterID))," +
            "(8, (:isCompleted), (:chapterID))," +
            "(9, (:isCompleted), (:chapterID))," +
            "(10, (:isCompleted), (:chapterID))," +
            "(11, (:isCompleted), (:chapterID))," +
            "(12, (:isCompleted), (:chapterID))," +
            "(13, (:isCompleted), (:chapterID))," +
            "(14, (:isCompleted), (:chapterID))," +
            "(15, (:isCompleted), (:chapterID))," +
            "(16, (:isCompleted), (:chapterID))," +
            "(17, (:isCompleted), (:chapterID))," +
            "(18, (:isCompleted), (:chapterID))," +
            "(19, (:isCompleted), (:chapterID))," +
            "(20, (:isCompleted), (:chapterID))," +
            "(21, (:isCompleted), (:chapterID))," +
            "(22, (:isCompleted), (:chapterID))," +
            "(23, (:isCompleted), (:chapterID))," +
            "(24, (:isCompleted), (:chapterID))" +
            "")
    void insertPagesOfChapter24(int chapterID, boolean isCompleted);

    @Delete
    void delete(Chapter chapter);

    @Query("DELETE FROM Chapter")
    void deleteAll();

    @Query("DELETE FROM Chapter WHERE ID = (:chapterID)")
    void deleteByID(int chapterID);
}
