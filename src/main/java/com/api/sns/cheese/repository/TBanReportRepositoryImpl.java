package com.api.sns.cheese.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TBanReportRepositoryImpl implements TBanReportRepository {
    private TBanReportMapper tBanReportMapper;

    public TBanReportRepositoryImpl(TBanReportMapper tBanReportMapper) {
        this.tBanReportMapper = tBanReportMapper;
    }

    @Override
    public TBanReportMapper getMapper() {
        return this.tBanReportMapper;
    }
}