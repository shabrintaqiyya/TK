package apap.tugas.akhir.RumahSehat.service;

import java.util.List;
import java.util.Map;

import apap.tugas.akhir.RumahSehat.model.TagihanDataModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {
    @Override
	public List<List<Map<Object, Object>>> getCanvasjsChartData() {
		return TagihanDataModel.getCanvasjsDataList();
	}
}