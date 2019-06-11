查询importList
#sql("searchImportList")
		SELECT a.id,
		       a.import_status,
		       a.customer_target,
		       a.service_no,
		       a.customer_po,
		       a.service_type,
		       a.service_center,
		       a.repair_no,
		       a.repair_type,
		       a.status,
		       a.customer_name,
		       a.repair_center,
		       a.total_qty apply_qty,
		       a.contact_name,
		       a.phone,
		       a.mobile_phone,
		       a.email,
		       c.name creator,
		       a.create_date,
		       d.name repair_issuer,
		       a.repair_issue_date
		  FROM es_service_tbl a, pf_user_relation_view c, pf_user_relation_view d
		 WHERE   a.create_by = c.cur_id(+)
		       AND a.repair_issue_by = d.cur_id(+)
		       AND a.status = 'APPROVE'
		       AND a.import_status IS NOT NULL
		       AND a.flag = '1'
		       AND a.backup_flag IS NULL
	          #if(serialNo)
	            AND EXISTS (SELECT * FROM es_service_item_tbl  WHERE flag = '1' AND service_id = a.id AND serial_no LIKE  #para(serialNo) )
	          #end
	           #if(center)
	               AND A.REPAIR_CENTER = #para(center)
	          #end
	            #if(rMANo)
	               AND   A.REPAIR_NO = #para(rMANo)
	          #end
	           #if(status)
	               AND A.STATUS =  #para(status)
	          #end
	          
#end