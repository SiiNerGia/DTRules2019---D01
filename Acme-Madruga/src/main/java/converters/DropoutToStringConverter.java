
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Dropout;

@Component
@Transactional
public class DropoutToStringConverter implements Converter<Dropout, String> {

	@Override
	public String convert(final Dropout dropout) {
		String result;

		if (dropout == null)
			result = null;
		else
			result = String.valueOf(dropout.getId());

		return result;
	}

}
