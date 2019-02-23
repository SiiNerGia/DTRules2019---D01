
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Enrol;

@Component
@Transactional
public class EnrolToStringConverter implements Converter<Enrol, String> {

	@Override
	public String convert(final Enrol enrol) {
		String result;
		if (enrol == null)
			result = null;
		else
			result = String.valueOf(enrol.getId());
		return result;
	}

}