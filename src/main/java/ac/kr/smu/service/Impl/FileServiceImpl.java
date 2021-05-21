package ac.kr.smu.service.Impl;

import ac.kr.smu.config.WebConfig;
import ac.kr.smu.mapper.FileMapper;
import ac.kr.smu.service.FileService;
import ac.kr.smu.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.RuntimeErrorException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    @Override
    public List<FileVO> saveAll(int postId, List<MultipartFile> files) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String upload_date = sdf.format(date);
        upload_date.replace("-", File.separator);
        File uploadPath = new File(WebConfig.UPLOAD_PATH,upload_date);

        if(!uploadPath.exists()) //현재 날짜의 폴더가 없다면 생성 ex) 2021/04/03
            uploadPath.mkdir();

        for(MultipartFile file : files){//파일을 하나씩 저장
            String fileName = file.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            FileVO fileVO = FileVO.builder().postId(postId).name(fileName).uuid(uuid.toString())
                    .uploadPath(uploadPath.toPath().toString()).build();

            fileName = uuid.toString() + "_" + fileName;
            File uploadFile = new File(uploadPath,fileName);
            try{
                file.transferTo(uploadFile);
                fileMapper.save(fileVO);
            }catch (IOException e){e.printStackTrace();}
        }
        return null;
    }
    @Override
    public FileSystemResource getFileSystemResource(int id) {
        FileSystemResource resource = new FileSystemResource(fileMapper.findById(id).getPath());

        if(!resource.exists())
            return null;

        return resource;
    }
    @Override
    public void deleteByPostId(int postId) {
        List<FileVO> fileList = fileMapper.findByPostId(postId);

        fileMapper.deleteByPostId(postId);
        fileList.stream().forEach(f ->{
            File file = new File(f.getPath());
            file.delete();
        });
        throw new RuntimeException();
    }
}