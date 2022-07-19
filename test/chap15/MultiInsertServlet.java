package chap15;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dao.MessageDao;
import guestbook.model.Message;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

/**
 * Servlet implementation class MultiInsertServlet
 */
@WebServlet("/MultiInsertServlet")
public class MultiInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		String []arr = {"축하드립니다.", "감축합니다.", "좋은 일정이었습니다.", "잘 둘러보았습니다.", "수고하셨습니다."};
		List<String> list = Arrays.asList(arr);
		
		String first[] = {"김","이","박","최","주","임","엄","성","남궁","독고","황","황보","송","오","유","류","윤","장","정","추"};  
        String middle[] = {"","숙","갑","영","순","선","원","우","이","운","성"};  
    	String last[] = {"영","수","희","빈","민","정","순","주","연","영"};  
    	
    	String name = "";
    			
		MessageDao dao = MessageDao.getInstance();
		try {
			conn = ConnectionProvider.getConnection();
			
			System.out.println("conn : " + conn);
			
			for (int i=0; i<100; i++) {
				Message message = new Message();
				
				Collections.shuffle(list);
				message.setMessage(list.get(0));
				
				name = first[(int)Math.floor(Math.random() * first.length)];
				name += middle[(int)Math.floor(Math.random() * middle.length)];
				name += last[(int)Math.floor(Math.random() * last.length)];
				
				message.setGuestName(name);
		
				int randomPw = (int)(Math.random() * 100000);
				
				message.setPassword(randomPw + "");
				
				dao.insert(conn, message);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
