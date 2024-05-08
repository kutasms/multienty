package com.chia.multienty.core.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

@Slf4j
class RsaUtilTest {
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjgpCvt/I99QL+IEFKMffCZwUytatFVUN+cJKCFJFktLzH2l61ysc2nfWVMbmn+mRFodL+fmb9rbufHxFvvZC+Ee8csz93s5QKB8ocx8Iqb6Qh9+ji6KfcydtrF6XsVldVSaleek7dQUgXwNgDWMYCd8ci5BhggWegUAoBUH6ycnLJ71Y5LNfk+GLu+bcNow5bMWQvM/YQviJ9fFRPt3rkiyxU7lJhb7VMw8dQmbkvpM9IhIFFJZbWBFPzXxNRs4Bpx+jPpyhROSdoN04fUFKV1bn+WmUxkjeV0k/jojS8j0HGOH/VXYd66xEv05sjaVZz+8eW6b6ml8BJfNgBsWcwwIDAQAB";
    @Test
    void decryptByPublicKey() {
        log.info("QUERY_AUTH_CODE:queryauthcode@@@j5b48C1p2CMrr1bxEX1qSD6AcfSdG_QsWh5u4FM89Y-FuC35LmQBdGalWuk1jx41NUxAG9SE7KigfYNTJXfQ8A".replace("QUERY_AUTH_CODE:",""));
    }

    @Test
    void encryptByPrivateKey() throws Exception {

    }

    @Test
    void decryptByPrivateKey() throws Exception {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCOCkK+38j31Av4gQUox98JnBTK1q0VVQ35wkoIUkWS0vMfaXrXKxzad9ZUxuaf6ZEWh0v5+Zv2tu58fEW+9kL4R7xyzP3ezlAoHyhzHwipvpCH36OLop9zJ22sXpexWV1VJqV56Tt1BSBfA2ANYxgJ3xyLkGGCBZ6BQCgFQfrJycsnvVjks1+T4Yu75tw2jDlsxZC8z9hC+In18VE+3euSLLFTuUmFvtUzDx1CZuS+kz0iEgUUlltYEU/NfE1GzgGnH6M+nKFE5J2g3Th9QUpXVuf5aZTGSN5XST+OiNLyPQcY4f9Vdh3rrES/TmyNpVnP7x5bpvqaXwEl82AGxZzDAgMBAAECggEAaVLKc2bVSo994OMqxDaQ+Ds4X8AXz/2lw1GCO/FljZ3GkcBRSkO5vXWnBG4IjQNQhngEqnOgZqz24m/z98n7lsb6wdWnWhN59IZGJDca4WNhIJ+AUgZgXa8a7/mkWel9/6oMgf2kDP2kJ1AFZWfZZ1h9fi+tXhB0nDrKcjuejwlrt+Y81z/ga7RhuRQyBAtYnUuwQDysnsB/IkhtcGpgHeE5i09DZcW2+wuzqFHvtOtYsGzIsi6eL+94oY1dgSMCsajtM+viUOPOhX7MiMeKjIvbzJs5bp0KbYFHmR2IGwHvmwTOoFXxYRhA32u48nI80Vi5KHlZ3jez6KKqgG+6gQKBgQDK4M1QW3bf1ev3IzKw6HZUgTOEYuGgYYCWpSAyAXwHGXBZ7U+WINO8tOD4jkd3H+/wh75VM0kkSuBMSfoavJTzU0EgALnj1F64+XiY9gFekcNZSSbhbW1Y7xCX7zoVmDuboHn0D3eyI62kCl/TYos/4CsPAINrAZqAHZLibu5hEwKBgQCzO2avJGIn5XbpNnUbOd7fX/1xSPvpDfdRo5a73HmC0vUnWpD8NmhB79kQDaB9/5f/D2ofWtNgZt0lORgLDzmFO001UGxOJ+YWq9UND6rdBWkzknMB2b1yQrcZplGJJ3cB1TmdS1JswW7IMmPGq/sRC+jWhPP3/PnuPtrQ+oH7kQKBgEHgx6q9WJeMnofsvQwnIC2GmtT1SvDKWHgSfKbgRocDorEA12QXmsnfLmJnyRsMmtxPJ+YAtlJ7oeQD7Zv6+p8cvLwIoFQz5OJJ1/hLgdxUlsuKbn6rCuPjFU9Q1lqmyUxayt8Omr6UyVDIMZ+xkygVwcWoVAROS/4oHEc84FlTAoGAWmFh7n/SX3v+8qtbcldEZC/rhezf2y4HwUtU/3a2b5ohta6d3hExkc55DvdQLzlsVFpZkWYjoUE90z9vOGR9BQpabMAZXXARPu13ihaUes/3tOHszMoYFj4+Rtjv1NHp2SrE2uTYs673Rkzis5SWDoqK52I0BKG1Bsb7YNZyJYECgYEAxx7pLScpd9hOaG1jCEHLs31Om1qqfbDg258IYw62RICpWCH4ZewNBMNEx+jwnwgnj27fQ6csH6Y8yQOGfP9vFjh6gUn7Acq9Q+fFhFQ0IQ0LzVE9vPHlmQqRjbCu0nxfhjgfHCquOtWfRPF9h9KHtMw4tCcHX6GuUlF10qzDOtk=";
        String decrypted = "PvfJtyWv3DPnLiX3AJHaOTf2oFEoA4/q1OKSNBtazVEEos/bcu5DH4t1KOSQCvN5Mubg00UoWZWwm893ZJ9X+WbpifyhKcPKgoSn324G3+9UEEg5Y4Ow/lGMWpxbCsyXNjuvfqH1cpCrz6uUzPnfA0jJfOCCGSQbANuRg5KuphNt0IXR8Je0MGBqjXw4QVoNH5hcz1/v1yxjs0sh2gTAscGGViGFVUicHo/qT8lcA62GO7632j6Otca/Dbu+DyRiSkB+VfQXtSgz6336SIGeg9bzRmbx+MwlZ7iR3dE+cdHgPXabMR5Ypi6QVATe9IR2pcaEvmwUyRasE1gToOJ7Chh307lv/NzWg1zfMWYK8iwjqED+rjD4F7puWKwZ9lgrpkpZRrCdYWFC9Jrrbkho9ARWbsQP3M2JEQBNNKtn8eKCNwiGvUnu0LLiC5ZQ22D22s6X6rLRUE9PrEHCWifFkfUMTG7vhETmPLLlHFWh/EBQmcN1XsLD89QwZl+gtQjkAq2wjmadzPuz+3StPwZPd5mbLpDisCM1+U6mm67VTCin2yPSQdzWc+mNgYVE8oY/1+haoT5Ar9VZ2zzbiWexoJQVp7/ZfvekLbth60Y0Rut/d62jVJjg5AcIaLs20A/X3qb+Beikhue4hxC0CJC7j9B9pVhLWxxi0EqrEEYHIitE27OA0UrTIYGaK0HoxaZ0ZYDTOXx8fWIaa3vYOW8Lcty8a/q4CYJuQjALEA7E2sPmAjLXLkXgMyXFQxaYUe0Me1q+beAThnBdipQyiKWc/W+0p8rM0ii6NRfV6GrPPRaAUnFUtNwqjFM2JEOPsVDsvy/c6+/x5Uzj3jk2h5QK54bYCsE8necdH8TEyWAXK/1ieswWxf1hpGM6bPnneqPht5zcKhYwxLysW79WFyTSJI/iJLY/ObJgg9L1JXxowM5hEeXpq1bE886tCQJL3pOXvAm6lqvIai3xt/z+V6nhoEPDj7JutIw5aBQ+i/k6Eg/+vQoEUGishiExZZeHFhgmM2JrlZXcBSMjUeEWgQuUrWRTvYV2B8RQO1LvslaqaaxjeK8zrZXOGl+qoWSv8oW/CjykmUaAvWy73DhWaiS40s5CRGNXrwU6uclfBNk2jE6EmqLXp7zxzWLYyGoymX1oH084RXad2oPDIMz137i7WOgJURJY8pEbv7s+ktlscHsqKYdoOproW8USgd5IokH2wfQdbRyptTEMi3OYmDaqhBiHSgS//B9fnE/wE/VRmllu15KN1XaAGkaHNcc5s9dhMYzi7r7YGaaU0fabwIOQ4WuuMtqLaTMz9YZaTnns386x81eevcKW0H1ttbGzVQrWqlf+FfteJS8dOmYSNGVi2Q==";
        byte[] bytes1 = RsaUtils.decryptByPrivateKey(decrypted, privateKey);
        log.info(new String(bytes1));
    }

    @Test
    void encryptByPublicKey() throws Exception {
        log.info("公钥长度:{}", publicKey.length());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");
//        jsonObject.put("code", "5082");
        jsonObject.put("mode", 1);
        String text = jsonObject.toJSONString();
        log.info("明文长度:{}",text.getBytes().length);
        String encrypted = RsaUtil.encryptByPublicKey(
                publicKey, text);
        log.info("加密后数据:{}", encrypted);
    }

    @Test
    void generateKeyPair() throws NoSuchAlgorithmException {
        RsaUtil.RsaKeyPair rsaKeyPair = RsaUtil.generateKeyPair();
        log.info("RSA 2048 PUBLIC KEY:{}",rsaKeyPair.getPublicKey());
        log.info("RSA 2048 PRIVATE KEY:{}",rsaKeyPair.getPrivateKey());
    }
}