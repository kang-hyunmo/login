package com.example.board.cont;

import com.example.board.dto.BoardDto;
import com.example.board.dto.MemberDto;
import com.example.board.dto.ReplyDto;
import com.example.board.dto.SearchDto;
import com.example.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//       }else {
//            bList=bSer.getBoardList(sDto); //검색 클릭
//        }



//        동적쿼리
//        bList=bSer.getBoardListSearch(sDto);
        bList=bSer.getBoardListSearchNew(sDto);

        if(bList!=null){
            //페이지 정보
            String pageHtml= bSer.getPaging(sDto);

            model.addAttribute("paging",pageHtml);
            model.addAttribute("bList",bList);  //js(json), each문
            return "board/list";
        }
        return "redirect:/";
    }
//    @GetMapping("/detail/{bnum}")
//    public String detail(@PathVariable("bnum") Integer bnum, Model model) {
//        log.info("bnum: {}", bnum);
//        return null;
//    }
    @GetMapping("/detail")
    public String detailParam(@RequestParam("b_num") Integer b_num, Model model) {
        log.info("b_num: {}", b_num);
        if (b_num==null || b_num<1) {
            return "redirect:/board";

        }
        BoardDto board= bSer.getBoardDetail(b_num);
        log.info("board: {}", board);
        if(board==null){
            return "redirect:/board";
        }else {
            model.addAttribute("board",board);
            return "board/detail";
        }

    }
    @GetMapping("/delete")
    public String boardDelete(@RequestParam("b_num") Integer b_num, RedirectAttributes rttr) {
        log.info("delete b_num: {}", b_num);
        if(b_num==null || b_num<1) {
            return "redirect:/board";
        }
        if (bSer.boardDelete(b_num)){
        rttr.addFlashAttribute("msg",b_num+"번 삭제 성공"); //한번만 출력
        /*rttr.addAttribute("msg",b_num+"번 삭제 성공");*/ //여러번 출력
        return "redirect:/board";
        }else {
            rttr.addAttribute("msg",b_num+"번 삭제 실패");
            return "redirect:/board/detail?b_num="+b_num;
        }

    }
    @PostMapping("/reply")
    @ResponseBody
    public String insertReply(@RequestBody ReplyDto replyDto, HttpSession session){
        log.info("insert r_bnum: {}", replyDto.getR_bnum());
        log.info("insert r_contents: {}", replyDto.getR_contents());
        String id=((MemberDto)session.getAttribute("member")).getM_id();
        log.info("insert r_writer: {}", id);
        return "성공";
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
