
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.MessageBox;

@Component
@Transactional
public class MessageBoxToStringConverter implements Converter<MessageBox, String> {

	@Override
	public String convert(final MessageBox box) {
		String result;
		if (box == null)
			result = null;
		else
			result = String.valueOf(box.getId());
		return result;
	}
}
