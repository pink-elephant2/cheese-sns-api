package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TBanReport;
import com.api.sns.cheese.domain.TBanReportExample;
import com.api.sns.cheese.domain.TBanReportKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TBanReportRepository extends BaseRepository<TBanReportKey, TBanReport, TBanReportExample> {
}