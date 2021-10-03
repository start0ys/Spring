package com.oracle.board.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.board.dao.BDao;
import com.oracle.board.dto.BDto;



public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto board = dao.contentView(bId);
		
		model.addAttribute("mvc_board", board);
		
		
	}

}
