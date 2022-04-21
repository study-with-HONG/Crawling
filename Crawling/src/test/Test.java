package test;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import dto.MovieDto;

public class Test {
	public static void main(String[] args) throws Exception {
		// Jsoup:각종 사이트에서 데이터를 취합할 수 있는 Library
		// WebContent > WEB-INF > lib에 jsoup jar파일 추가
		Document dc = Jsoup.connect("http://www.cgv.co.kr/movies/?lt=1&ft=0").get();
		Elements titles = dc.select("div.box-contents strong.title");
		Elements percents = dc.select("div.score strong.percent span");
		
//		System.out.println(titles);
//		System.out.println(percents);
		
		for(int i = 0; i<19; i++) {
			Element title = titles.get(i);
			Element percent = percents.get(i);
			
			String t = title.text();
			String p = percent.text();
			double dp = Double.parseDouble(p.substring(0, p.length()-1));
			
			MovieDto dto = new MovieDto(t, dp);
			System.out.println(dto);
		}
	}
}