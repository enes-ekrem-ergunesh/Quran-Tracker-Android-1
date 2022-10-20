package com.example.quran_tracker_1.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PageDao {
    @Query("SELECT * FROM Page")
    List<Page> getAll();

    @Query("SELECT * FROM Page WHERE ChapterID = (:chapterId)")
    List<Page> getAllByChapterID(int chapterId);

    @Query("SELECT * FROM Page " +
            "INNER JOIN Chapter ON Chapter.ID=Page.ChapterID " +
            "WHERE Chapter.RecitationID = (:recitationID)")
    List<Page> getAllByRecitationID(int recitationID);

    @Query("SELECT * FROM Page WHERE Page.ID=(:pageID)")
    Page getByID(int pageID);

    @Query("SELECT * FROM Page WHERE isCompleted=0 AND ChapterID=(:chapterId)")
    List<Page> getUnCompletedPages(int chapterId);


    @Query("UPDATE Page SET isCompleted = (:isCompleted) WHERE ID = (:pageID)")
    void setCompleted(int pageID, boolean isCompleted);

    @Query("UPDATE Page SET isCompleted = (:isCompleted) WHERE ChapterID = (:chapterID)")
    void setCompletedByChapterId(int chapterID, boolean isCompleted);


    @Insert
    void insert(Page page);

    @Insert
    void insertAll(Page... pages);


    @Delete
    void delete(Page page);

    @Query("DELETE FROM Page")
    void deleteAll();

    @Query("DELETE FROM Page WHERE ChapterID = (:chapterID)")
    void deleteAllByChapterID(int chapterID);
}