package data;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import dto.MovieDto;

public class CGV{
	// static(정적)을 붙히면 CGV.getCGVdata처럼 불러올 수 있다.
	public static List<MovieDto> getCGVdata() throws Exception{
		Document dc = Jsoup.connect("http://www.cgv.co.kr/movies/?lt=1&ft=0").get();
		Elements titles = dc.select("div.box-contents strong.title");
		Elements percents = dc.select("div.score strong.percent span");
		
		List<MovieDto> list = new ArrayList<MovieDto>();
		
		for(int i = 0; i<19; i++) {
			Element title = titles.get(i);
			Element percent = percents.get(i);
			
			String t = title.text();
			String p = percent.text();
			double dp = Double.parseDouble(p.substring(0, p.length()-1));
//				  방법2	Double.parseDouble(p.replace("%", "").trim());
			
			MovieDto dto = new MovieDto(t, dp);
			list.add(dto);
		}
		return list;
	}
}