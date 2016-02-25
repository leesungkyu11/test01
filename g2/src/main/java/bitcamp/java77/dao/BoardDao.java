package bitcamp.java77.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java77.domain.Board;

public interface BoardDao {
  List<Board> selectList(Map<String,Object> paramMap);
  
  int insert(Board board);
  
  int delete(Map<String,Object> paramMap);
  
  int update(Board board);

  Board selectOne(int no);
}







