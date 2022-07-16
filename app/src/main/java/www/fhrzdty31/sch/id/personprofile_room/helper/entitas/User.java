package www.fhrzdty31.sch.id.personprofile_room.helper.entitas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int id;

    public String name;
    public String nomor;
    public String email;
}
