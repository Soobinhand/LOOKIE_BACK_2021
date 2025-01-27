package ac.kr.smu.service;

import ac.kr.smu.mapper.PostMapper;
import ac.kr.smu.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    public void save(PostVO postVO);
    public List<PostVO> findAll();
    public PostVO findById(int id);
    public PostVO update(PostVO postVO);
    public void delete(int id);
}
@Service
class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    public void save(PostVO postVO){
        postMapper.save(postVO);
    }

    @Override
    public List<PostVO> findAll() {
        return postMapper.findAll();
    }

    @Override
    public PostVO findById(int id) {
        return postMapper.findById(id);
    }

    @Override
    public PostVO update(PostVO postVO) {
        return postMapper.update(postVO);
    }
    @Override
    public void delete(int id) {
        postMapper.delete(id);
    }
}
