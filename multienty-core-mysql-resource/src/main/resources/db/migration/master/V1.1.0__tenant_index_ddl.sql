ALTER TABLE `saas_master`.`mt_tenant`
ADD UNIQUE INDEX `IX_TENANT_UN_UNI` (`username` ASC) VISIBLE;
;