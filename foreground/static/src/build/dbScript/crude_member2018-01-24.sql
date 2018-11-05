delete a from accounts a where a.m_member_id=a.s_member_id;
UPDATE members m set m.password= MD5(CONCAT(MD5(m.`password`),'SinoB2B'));