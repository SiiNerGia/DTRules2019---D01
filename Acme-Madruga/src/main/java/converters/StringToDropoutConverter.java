
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.DropoutRepository;
import domain.Dropout;

@Component
@Transactional
public class StringToDropoutConverter implements Converter<String, Dropout> {

	@Autowired
	DropoutRepository	dropoutRepository;


	@Override
	public Dropout convert(final String text) {
		Dropout result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.dropoutRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
