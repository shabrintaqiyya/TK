package apap.tugas.akhir.RumahSehat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;

import apap.tugas.akhir.RumahSehat.service.TagihanChartService;
 
@Controller
@RequestMapping("/user")
public class TagihanChartController {
    @Autowired
	private TagihanChartService tagihanChartService;
 
	@RequestMapping(method = RequestMethod.GET)
	public String springMVC(ModelMap modelMap) {
		List<List<Map<Object, Object>>> tagihanDataList = tagihanChartService.getCanvasjsChartData();
		modelMap.addAttribute("dataPointsList", tagihanDataList);
		return "tagihan-chart";
	}
}
