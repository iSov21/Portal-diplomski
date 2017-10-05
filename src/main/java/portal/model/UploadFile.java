package portal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class UploadFile {
    private long id;
    private String fileName;
    private byte[] data;
 
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getFileName() {
        return fileName;
    }
 
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
 
    public byte[] getData() {
        return data;
    } 
    public void setData(byte[] data) {
        this.data = data;
    }
}
