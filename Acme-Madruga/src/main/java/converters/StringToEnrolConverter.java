
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.Enrol;
import repositories.EnrolRepository;

@Component
@Transactional
public class StringToEnrolConverter implements Converter<String, Enrol> {

	@Autowired
	EnrolRepository	enrolRepository;


	@Override
	public Enrol convert(final String text) {
		Enrol result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.enrolRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
