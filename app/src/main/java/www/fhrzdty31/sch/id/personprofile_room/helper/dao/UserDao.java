package www.fhrzdty31.sch.id.personprofile_room.helper.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

import www.fhrzdty31.sch.id.personprofile_room.helper.entitas.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("INSERT INTO user (name, nomor, email) VALUES (:name, :nomor, :email)")
    void insertAll(String name, String nomor, String email);

    @Query("UPDATE user SET name = :name, email = :email, nomor = :nomor WHERE id = :id")
    void update(int id, String name, String nomor, String email);

    @Delete
    void delete(User user);
}
