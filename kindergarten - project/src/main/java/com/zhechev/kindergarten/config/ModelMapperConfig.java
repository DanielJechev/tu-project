package com.zhechev.kindergarten.config;

import com.zhechev.kindergarten.dtos.ChildrenCreateModel;
import com.zhechev.kindergarten.dtos.ChildrenCreateServiceModel;
import com.zhechev.kindergarten.dtos.GroupModel;
import com.zhechev.kindergarten.dtos.GroupServiceModel;
import com.zhechev.kindergarten.dtos.SubjectModel;
import com.zhechev.kindergarten.dtos.SubjectServiceModel;
import com.zhechev.kindergarten.models.Education;
import com.zhechev.kindergarten.models.Gender;
import com.zhechev.kindergarten.models.Vid;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
        initMapper(mapper);
        initMap(mapper);
        initMapSubject(mapper);
    }

    private static void initMapper(ModelMapper mapper) {
        Converter<String, Gender> stringToGenderConverter =
                ctx -> Gender.valueOf(ctx.getSource().toUpperCase());

        mapper.createTypeMap(ChildrenCreateModel.class, ChildrenCreateServiceModel.class)
                .addMappings(map -> map
                        .using(stringToGenderConverter)
                        .map(
                                ChildrenCreateModel::getGender,
                                ChildrenCreateServiceModel::setGender
                        )
                );
    }

    private static void initMap(ModelMapper mapper) {
        Converter<String, Vid> stringVidConverter =
                ctx -> Vid.valueOf(ctx.getSource().toUpperCase());

        mapper.createTypeMap(GroupModel.class, GroupServiceModel.class)
                .addMappings(map -> map
                        .using(stringVidConverter)
                        .map(
                                GroupModel::getVid,
                                GroupServiceModel::setVid
                        )
                );
    }

    private static void initMapSubject(ModelMapper mapper) {
        Converter<String, Education> stringToEducationConverter =
                ctx -> Education.valueOf(ctx.getSource().toUpperCase());

        mapper.createTypeMap(SubjectModel.class, SubjectServiceModel.class)
                .addMappings(map -> map
                        .using(stringToEducationConverter)
                        .map(
                                SubjectModel::getEducation,
                                SubjectServiceModel::setEducation
                        )
                );
    }

    @Bean
    public ModelMapper modelMapper() {
        return mapper;
    }
}
