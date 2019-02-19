package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;

@Component
@Transactional
public class AuthorityToStringConverter implements Converter<Authority, String> {
	
	public String convert(Authority authority) {
		String result;
		
		if(authority == null){
			result = null;
		}else{
			result = String.valueOf(authority.getAuthority());
		}
		
		return result;
	}

}