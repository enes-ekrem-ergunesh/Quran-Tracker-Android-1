package com.example.quran_tracker_1.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecitationDao {
    @Query("SELECT * FROM Recitation")
    List<Recitation> getAll();

    @Query("SELECT * FROM Recitation WHERE Recitation.id=(:recitationID)")
    Recitation getByID(int recitationID);

    @Query("SELECT * FROM Recitation WHERE Recitation.name=(:recitationName)")
    Recitation getByName(String recitationName);

    @Query("SELECT * FROM Recitation WHERE Recitation.dateModified=(:dateModified)")
    Recitation getByDateModified(long dateModified);



    @Query("UPDATE Recitation SET isCompleted = (:isCompleted) WHERE ID=(:recitationID)")
    void setCompleted(int recitationID, boolean isCompleted);

    @Query("UPDATE Recitation SET name = (:name) WHERE ID=(:recitationID)")
    void setName(int recitationID, String name);


    @Insert
    void insert(Recitation recitation);

    @Query("INSERT INTO Chapter(Name, Number, isCompleted, RecitationID)" +
            "VALUES" +
            "(NULL, 1, (:isCompleted), (:recitationID))," +
            "(NULL, 2, (:isCompleted), (:recitationID))," +
            "(NULL, 3, (:isCompleted), (:recitationID))," +
            "(NULL, 4, (:isCompleted), (:recitationID))," +
            "(NULL, 5, (:isCompleted), (:recitationID))," +
            "(NULL, 6, (:isCompleted), (:recitationID))," +
            "(NULL, 7, (:isCompleted), (:recitationID))," +
            "(NULL, 8, (:isCompleted), (:recitationID))," +
            "(NULL, 9, (:isCompleted), (:recitationID))," +
            "(NULL, 10, (:isCompleted), (:recitationID))," +
            "(NULL, 11, (:isCompleted), (:recitationID))," +
            "(NULL, 12, (:isCompleted), (:recitationID))," +
            "(NULL, 13, (:isCompleted), (:recitationID))," +
            "(NULL, 14, (:isCompleted), (:recitationID))," +
            "(NULL, 15, (:isCompleted), (:recitationID))," +
            "(NULL, 16, (:isCompleted), (:recitationID))," +
            "(NULL, 17, (:isCompleted), (:recitationID))," +
            "(NULL, 18, (:isCompleted), (:recitationID))," +
            "(NULL, 19, (:isCompleted), (:recitationID))," +
            "(NULL, 20, (:isCompleted), (:recitationID))," +
            "(NULL, 21, (:isCompleted), (:recitationID))," +
            "(NULL, 22, (:isCompleted), (:recitationID))," +
            "(NULL, 23, (:isCompleted), (:recitationID))," +
            "(NULL, 24, (:isCompleted), (:recitationID))," +
            "(NULL, 25, (:isCompleted), (:recitationID))," +
            "(NULL, 26, (:isCompleted), (:recitationID))," +
            "(NULL, 27, (:isCompleted), (:recitationID))," +
            "(NULL, 28, (:isCompleted), (:recitationID))," +
            "(NULL, 29, (:isCompleted), (:recitationID))," +
            "(NULL, 30, (:isCompleted), (:recitationID))" +
            "")
    void insertChaptersOfRecitation(int recitationID, boolean isCompleted);


    @Delete
    void delete(Recitation recitation);

    @Query("DELETE FROM Recitation")
    void deleteAll();

    @Query("DELETE FROM Recitation WHERE ID = (:recitationID)")
    void deleteByID(int recitationID);

    @Query("DELETE FROM Chapter WHERE RecitationID = (:recitationID)")
    void deleteChaptersOfRecitation(int recitationID);

}