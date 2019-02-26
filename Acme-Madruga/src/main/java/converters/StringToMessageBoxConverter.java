
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.MessageBox;
import repositories.MessageBoxRepository;

@Component
@Transactional
public class StringToMessageBoxConverter implements Converter<String, MessageBox> {

	@Autowired
	MessageBoxRepository messageBoxRepository;


	@Override
	public MessageBox convert(final String text) {
		MessageBox result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.messageBoxRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
