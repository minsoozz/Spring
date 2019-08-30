package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dto.BbsDto;
import dto.BbsParam;
import dto.MemberDto;

public class mainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String res = "mybatis/config.xml"; /* 이걸 읽겠다 */

		try {

			/* mybatis 설정 파일을 읽어 들인다 */
			InputStream is = Resources.getResourceAsStream(res);

			/* SqlSessionFactory의 객체를 취득 */
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

			/* SqlSession의 객체를 취득 */
			SqlSession session = factory.openSession();

			/* // insert 
			
			  MemberDto dto = new MemberDto("cutebohan1","q1w2e3", "@");
			  
			  앞 : String은 Mapper의 id이다, 뒤 : 넣고 싶은 Object 값 int n =
			  session.insert("add",dto);
			  
			  if(n>0) { session.commit(); System.out.println("추가 성공!"); } else {
			  session.rollback(); System.out.println("추가 실패"); }
			 */
			
			/*//delete 
			
			  String findId = "cutebohan2"; int n = session.delete("remove",findId);
			  
			  if(n>0) { session.commit(); System.out.println("삭제 성공");
			  
			  } else { session.rollback(); System.out.println("삭제 실패");
			  
			  }
			
			  MemberDto dto = new MemberDto("jieun", null, null); int n =
			  session.delete("removeobj",dto);
			  
			  if(n>0) { session.commit(); System.out.println("성공"); } else {
			  session.rollback(); System.out.println("실패"); }
			 		
		
			
			  String str = "바꼈나요?";
			  
			  int n = session.update("update", str);
			  
			  if(n>0) { session.commit(); System.out.println("성공"); } else {
			  session.rollback(); System.out.println("실패"); }
			 */
				
			/* //select 
			
			  String fId = "cutebohan0"; MemberDto dto = session.selectOne("find",fId);
			  System.out.println(dto.toString());
			 */
			
			
			/* //select list 
			
			 List<MemberDto> list = session.selectList("allList");
			 
			  for (int i = 0; i < list.size(); i++) { System.out.println(list.get(i)); }
			 
			 */
			
			/* search */
			BbsParam bp = new BbsParam("1","","title");
			
			List<BbsDto> bbslist = session.selectList("search",bp);
			
			for (BbsDto bbs : bbslist) {
				System.out.println(bbs.toString());
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
