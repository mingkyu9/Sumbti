package app.sum.bti.mainView.service;

import app.sum.bti.mainView.mapper.mainViewMapper;
import app.sum.bti.mainView.vo.mainViewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class mainViewService {

   private final mainViewMapper mapper;


   // 커플랭킹 가져오기
   public List<mainViewVO.LankList> coLank() throws SQLException {
        return mapper.coLank();
   }

   // 프렌드랭킹 가져오기

    public List<mainViewVO.LankList> frLank() throws SQLException {
        return mapper.frLank();
    }
}
