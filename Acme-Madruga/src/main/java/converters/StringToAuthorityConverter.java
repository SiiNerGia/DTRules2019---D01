package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;

@Component
@Transactional
public class StringToAuthorityConverter implements Converter<String, Authority> {

	@Override
	public Authority convert(String text) {		
		Authority result;
		
		try{
			if(StringUtils.isEmpty(text)){
				result = null;
			}else{
				result = new Authority();
				result.setAuthority(text);
			}
		}catch(Throwable oops){
			throw new IllegalArgumentException(oops);
		}
		
		return result;		
	}

}