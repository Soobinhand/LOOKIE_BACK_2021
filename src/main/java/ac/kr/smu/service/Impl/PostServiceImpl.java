package ac.kr.smu.service.Impl;

import ac.kr.smu.mapper.PostMapper;
import ac.kr.smu.service.FileService;
import ac.kr.smu.service.PostService;
import ac.kr.smu.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    public void save(PostVO postVO) {
        postMapper.save(postVO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<PostVO> findAll() {
        return postMapper.findAll();
    }

    @Override
    public PostVO findById(int id) {
        return postMapper.findById(id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        FileService fileService = null;
        fileService.deleteByPostId(id);
        postMapper.delete(id);
    }
    @Override
    public PostVO update(PostVO postVO) {
        return postMapper.update(postVO);
    }

   
}
