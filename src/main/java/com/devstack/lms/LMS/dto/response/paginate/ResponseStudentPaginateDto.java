package com.devstack.lms.LMS.dto.response.paginate;

import com.devstack.lms.LMS.dto.response.ResponseStudentDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseStudentPaginateDto {
    private long count;
    private List<ResponseStudentDto> dataList;
}
