package org.develop.app.batch.step.components.mappers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.develop.app.batch.dto.EstrazioneDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EstrazioneFieldSetMapper implements FieldSetMapper<EstrazioneDto> {

	@Override
	public EstrazioneDto mapFieldSet(FieldSet fieldSet) throws BindException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		
		EstrazioneDto lotto = new EstrazioneDto();

		try {
			cal.setTime(sdf.parse(fieldSet.readString(0)));
			lotto.setDate(cal);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lotto.setRuota(fieldSet.readString(1));
		lotto.setColumn1(fieldSet.readInt(2));
		lotto.setColumn2(fieldSet.readInt(3));
		lotto.setColumn3(fieldSet.readInt(4));
		lotto.setColumn4(fieldSet.readInt(5));
		lotto.setColumn5(fieldSet.readInt(6));
				
		return lotto;

	}

}