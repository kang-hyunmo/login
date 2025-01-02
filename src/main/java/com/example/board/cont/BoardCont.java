package com.example.board.cont;

import com.example.board.dto.BoardDto;
import com.example.board.dto.SearchDto;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardCont {
    //@Autowired
    private final BoardService bSer;
    //localhost/board/list
    //@GetMapping("/list")
    //localhost/board // ?pageNum=1
    @GetMapping
    public String list(SearchDto sDto, Model model) {
        log.info("before sDto: {}", sDto);
        //서비스 -->DB-->게시글들
        if(sDto.getPageNum()==null){
            sDto.setPageNum(1);
        }
        if(sDto.getListCnt()==null){
            sDto.setListCnt(BoardService.LISTCNT);
        }
        if (sDto.getStartIdx()==null){
            sDto.setStartIdx(0);
        }
        List<BoardDto> bList=null;
//        정적쿼리
//        if (sDto.getColname()==null || sDto.getKeyword()==null){
//            bList=bSer.getBoardList(sDto.getPageNum()); //페이징 클릭
//        }else {
//            bList=bSer.getBoardList(sDto); //검색 클릭
//        }


//        동적쿼리
//        bList=bSer.getBoardListSearch(sDto);
        bList=bSer.getBoardListSearchNum(sDto);

        if(bList!=null){
            //페이지 정보
            String pageHtml= bSer.getPaging(sDto);

            model.addAttribute("paging",pageHtml);
            model.addAttribute("bList",bList);  //js(json), each문
            return "board/list";
        }
        return "redirect:/";
    }
    @GetMapping("/detail/{bnum}")
    public String detail(@PathVariable("bnum") Integer bnum, Model model) {
        log.info("bnum: {}", bnum);
        return null;
    }
    @GetMapping("/detail")
    public String detailParam(@RequestParam("b_num") Integer b_num, Model model) {
        log.info("b_num: {}", b_num);
        return null;
    }
    @GetMapping("/write")
    public String write() {
        return "/board/write";
    }
    @PostMapping("/write")
    public String write(BoardDto board) {
        //DB에 글을 저장
        return "redirect:/board/list";    //get만 허용
    }
}
