// package apap.tugas.akhir.RumahSehat.controller;

// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.ModelMap;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// import apap.tugas.akhir.RumahSehat.service.CanvasjsChartService;

// @Controller
// @RequestMapping("/canvasjschart")
// public class CanvasjsChartController {
 
// 	@Autowired
// 	private CanvasjsChartService canvasjsChartService;
 
// 	@RequestMapping(method = RequestMethod.GET)
// 	public String springMVC(ModelMap modelMap) {
// 		List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
// 		modelMap.addAttribute("dataPointsList", canvasjsDataList);
// 		return "chart";
// 	}
 
// }   
