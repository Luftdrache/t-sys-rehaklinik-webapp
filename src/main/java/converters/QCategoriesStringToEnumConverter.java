package converters;

import com.tsystems.rehaklinik.types.QualificationCategories;
import org.springframework.core.convert.converter.Converter;


public class QCategoriesStringToEnumConverter implements Converter<String, QualificationCategories> {

    @Override
    public QualificationCategories convert(String source) {
        try {
            return QualificationCategories.valueOf(source.toUpperCase());
        }catch (IllegalArgumentException exception) {
            return QualificationCategories.NONE;
        }
    }
}
