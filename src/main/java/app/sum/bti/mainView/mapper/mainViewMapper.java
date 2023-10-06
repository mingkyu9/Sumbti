package app.sum.bti.mainView.mapper;

import app.sum.bti.mainView.vo.mainViewVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface mainViewMapper {

    // 커플 랭킹
    public List<mainViewVO.LankList> coLank() throws SQLException;

    //프렌드 랭킹
    public List<mainViewVO.LankList> frLank() throws SQLException;
}
