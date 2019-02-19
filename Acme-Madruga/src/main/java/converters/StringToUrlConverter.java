
package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Url;

@Component
@Transactional
public class StringToUrlConverter implements Converter<String, Url> {

	@Override
	public Url convert(final String text) {
		Url result;

		if (text == null)
			result = null;
		else
			try {
				result = new Url();
				result.setLink(URLDecoder.decode(text, "UTF-8"));
			} catch (final Throwable oops) {
				throw new IllegalArgumentException(oops);
			}

		return result;
	}

}
