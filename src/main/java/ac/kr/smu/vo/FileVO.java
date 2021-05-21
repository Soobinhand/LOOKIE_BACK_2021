package ac.kr.smu.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileVO {
    public int id;
    public String name; //파일의 이름
    public String uuid; //이름의 중복을 피하기 위해 앞에 붙는 랜덤한 문자열 ex)aad6af27-d71d-4a17-a627-b258cfe8b2fe
    public String uploadPath; //파일이 저장된 위치
    public int postId;
    public String getPath(){
        return uploadPath + "/" + uuid + "_" + name;
    }
}