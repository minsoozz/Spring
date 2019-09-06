package bit.com.a.util;
 
import java.io.File;
 
public class FileDelete {
     
     
    public static void main(String args){
         
        File file = new File(args);
        System.out.println("file : " + file);
        if( file.exists() ){ //파일존재여부확인
             
            if(file.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패");
            }
             
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
             
    }
 
 
}