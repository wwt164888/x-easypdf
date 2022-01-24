package wiki.xsx.core.pdf.doc;

import lombok.SneakyThrows;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;

import java.awt.image.BufferedImage;
import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

/**
 * pdf文档签名器
 * @author xsx
 * @date 2021/12/7
 * @since 1.8
 * <p>
 * Copyright (c) 2020-2022 xsx All Rights Reserved.
 * x-easypdf is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 * http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 * </p>
 */
public class XEasyPdfDocumentSigner {
    /**
     * pdf文档签名器参数
     */
    private final XEasyPdfDocumentSignerParam param = new XEasyPdfDocumentSignerParam();

    /**
     * 有参构造
     * @param pdfDocument pdf文档
     */
    XEasyPdfDocumentSigner(XEasyPdfDocument pdfDocument) {
        this.param.setPdfDocument(pdfDocument);
        this.param.setDocument(this.param.getPdfDocument().build());
    }

    /**
     * 设置签名信息
     * @param name 名称
     * @param location 位置
     * @param reason 原因
     * @param contactInfo 联系信息
     * @return 返回pdf文档签名器
     */
    public XEasyPdfDocumentSigner setSignerInfo(
            String name,
            String location,
            String reason,
            String contactInfo
    ) {
        this.param.getSignature().setName(name);
        this.param.getSignature().setLocation(location);
        this.param.getSignature().setReason(reason);
        this.param.getSignature().setContactInfo(contactInfo);
        return this;
    }

    /**
     * 设置签名过滤器
     * @param filter 过滤器
     * @param subFilter 子过滤器
     * @return 返回pdf文档签名器
     */
    public XEasyPdfDocumentSigner setSignFilter(
            SignFilter.Filter filter,
            SignFilter.SubFilter subFilter
    ) {
        if (filter!=null) {
            this.param.getSignature().setFilter(filter.filter);
        }
        if (subFilter!=null) {
            this.param.getSignature().setSubFilter(subFilter.filter);
        }
        return this;
    }

    /**
     * 设置签名图片
     * @param image 图片
     * @param marginLeft 图片左边距
     * @param marginTop 图片上边距
     * @param scalePercent 图片缩放比例
     * @return 返回pdf文档签名器
     */
    public XEasyPdfDocumentSigner setSignImage(
            BufferedImage image,
            float marginLeft,
            float marginTop,
            float scalePercent
    ) {
        this.param.setImage(image)
                .setImageMarginLeft(marginLeft)
                .setImageMarginTop(marginTop)
                .setImageScalePercent(scalePercent-100);
        return this;
    }

    /**
     * 设置签名证书
     * @param signAlgorithm 签名算法
     * @param keyStoreType 密钥库类型
     * @param certificate 证书文件
     * @param certificatePassword 证书密码
     * @return 返回pdf文档签名器
     */
    public XEasyPdfDocumentSigner setCertificate(
            SignAlgorithm signAlgorithm,
            KeyStoreType keyStoreType,
            File certificate,
            String certificatePassword
    ) {
        this.param.setSignAlgorithm(signAlgorithm)
                .setKeyStoreType(keyStoreType)
                .setCertificate(certificate)
                .setCertificatePassword(certificatePassword);
        return this;
    }

    /**
     * 设置签名内存大小（默认：250K）
     * @param preferredSignatureSize 签名内存大小
     * @return 返回pdf文档签名器
     */
    public XEasyPdfDocumentSigner setPreferredSignatureSize(int preferredSignatureSize) {
        this.param.setPreferredSignatureSize(preferredSignatureSize);
        return this;
    }

    /**
     * 设置自定义签名接口
     * @param customSignature 自定义pdfbox签名接口
     * @return 返回pdf文档签名器
     */
    public XEasyPdfDocumentSigner setCustomSignature(SignatureInterface customSignature) {
        this.param.setCustomSignature(customSignature);
        return this;
    }

    /**
     * 设置签名后pdf访问权限
     * @param accessPermissions pdf访问权限
     * @return 返回pdf文档签名器
     */
    public XEasyPdfDocumentSigner setAccessPermissions(int accessPermissions) {
        this.param.setAccessPermissions(accessPermissions);
        return this;
    }

    /**
     * 签名
     * @param pageIndex 签名页面索引
     * @param outputStream 输出流
     */
    @SneakyThrows
    public void sign(int pageIndex, OutputStream outputStream) {
        // 初始化参数
        this.param.init(pageIndex);
        try (
                // 创建字节数组输出流
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024)
        ) {
            // 创建任务文档
            PDDocument target = this.param.getDocument();
            // 设置文档信息及保护策略
            this.param.getPdfDocument().setInfoAndPolicyAndBookmark(target);
            // 保存文档
            target.save(byteArrayOutputStream);
            // 添加签名
            this.addSignature(target, outputStream);
        }
        // 关闭文档
        this.param.getPdfDocument().close();
    }

    /**
     * 添加签名
     * @param target 目标文档
     * @param outputStream 输出流
     */
    @SneakyThrows
    void addSignature(PDDocument target, OutputStream outputStream) {
        // 设置mdp权限
        this.setMdpPermission(target, this.param.getSignature(), this.param.getAccessPermissions());
        // 重置签名表单
        this.resetSignForm(target);
        // 添加签名
        target.addSignature(this.param.getSignature(), this.getSignatureInterface(), this.param.getSignatureOptions());
        // 保存文档
        target.saveIncremental(outputStream);
        // 关闭任务文档
        target.close();
    }

    /**
     * 重置签名表单
     * @param target 目标文档
     */
    void resetSignForm(PDDocument target) {
        PDAcroForm acroForm = target.getDocumentCatalog().getAcroForm();
        if (acroForm!=null&&acroForm.getNeedAppearances()) {
            if (acroForm.getFields().isEmpty()) {
                acroForm.getCOSObject().removeItem(COSName.NEED_APPEARANCES);
            }
        }
    }

    /**
     * 获取签名接口
     * @return 返回签名接口
     */
    @SneakyThrows
    private SignatureInterface getSignatureInterface() {
        return this.param.getCustomSignature()!=null?this.param.getCustomSignature():new DefaultSignatureImplement(this);
    }

    /**
     * 获取mdp权限
     * @param doc pdfbox文档
     * @return 返回mdp权限
     */
    private int getMdpPermission(PDDocument doc) {
        COSBase base = doc.getDocumentCatalog().getCOSObject().getDictionaryObject(COSName.PERMS);
        if (base instanceof COSDictionary) {
            COSDictionary permsDict = (COSDictionary) base;
            base = permsDict.getDictionaryObject(COSName.DOCMDP);
            if (base instanceof COSDictionary) {
                COSDictionary signatureDict = (COSDictionary) base;
                base = signatureDict.getDictionaryObject("Reference");
                if (base instanceof COSArray) {
                    COSArray refArray = (COSArray) base;
                    for (int i = 0; i < refArray.size(); ++i) {
                        base = refArray.getObject(i);
                        if (base instanceof COSDictionary) {
                            COSDictionary sigRefDict = (COSDictionary) base;
                            if (COSName.DOCMDP.equals(sigRefDict.getDictionaryObject("TransformMethod"))) {
                                base = sigRefDict.getDictionaryObject("TransformParams");
                                if (base instanceof COSDictionary) {
                                    COSDictionary transformDict = (COSDictionary) base;
                                    int accessPermissions = transformDict.getInt(COSName.P, 2);
                                    if (accessPermissions < 1 || accessPermissions > 3) {
                                        accessPermissions = 2;
                                    }
                                    return accessPermissions;
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 设置mdp权限
     * @param doc pdfbox文档
     * @param signature pdfbox签名
     * @param accessPermissions 签名后pdf访问权限
     */
    @SneakyThrows
    private void setMdpPermission(PDDocument doc, PDSignature signature, int accessPermissions) {
        List<PDSignature> signatureList = doc.getSignatureDictionaries();
        for (PDSignature sig : signatureList) {
            if (COSName.DOC_TIME_STAMP.equals(sig.getCOSObject().getItem(COSName.TYPE))) {
                continue;
            }
            if (sig.getCOSObject().containsKey(COSName.CONTENTS)) {
                throw new IOException("DocMDP transform method not allowed if an approval signature exists");
            }
        }

        COSDictionary sigDict = signature.getCOSObject();

        // DocMDP specific stuff
        COSDictionary transformParameters = new COSDictionary();
        transformParameters.setItem(COSName.TYPE, COSName.getPDFName("TransformParams"));
        transformParameters.setInt(COSName.P, accessPermissions);
        transformParameters.setName(COSName.V, "1.2");
        transformParameters.setNeedToBeUpdated(true);

        COSDictionary referenceDict = new COSDictionary();
        referenceDict.setItem(COSName.TYPE, COSName.getPDFName("SigRef"));
        referenceDict.setItem("TransformMethod", COSName.DOCMDP);
        referenceDict.setItem("DigestMethod", COSName.getPDFName("SHA1"));
        referenceDict.setItem("TransformParams", transformParameters);
        referenceDict.setNeedToBeUpdated(true);

        COSArray referenceArray = new COSArray();
        referenceArray.add(referenceDict);
        sigDict.setItem("Reference", referenceArray);
        referenceArray.setNeedToBeUpdated(true);

        // Catalog
        COSDictionary catalogDict = doc.getDocumentCatalog().getCOSObject();
        COSDictionary permsDict = new COSDictionary();
        catalogDict.setItem(COSName.PERMS, permsDict);
        permsDict.setItem(COSName.DOCMDP, signature);
        catalogDict.setNeedToBeUpdated(true);
        permsDict.setNeedToBeUpdated(true);
    }

    /**
     * 签名接口默认实现
     */
    public static class DefaultSignatureImplement implements SignatureInterface {

        /**
         * pdf签名器
         */
        private XEasyPdfDocumentSigner signer;

        /**
         * 提供者bc
         */
        private static final String PROVIDER = "BC";

        /**
         * 有参构造
         * @param signer pdf签名器
         */
        DefaultSignatureImplement(XEasyPdfDocumentSigner signer) {
            this.signer = signer;
        }

        /**
         * 签名
         * @param content 内容
         * @return 返回字节数组
         */
        @SneakyThrows
        @Override
        public byte[] sign(InputStream content) {
            Security.addProvider(new BouncyCastleProvider());
            char[] passwordCharArray = this.signer.param.getCertificatePassword().toCharArray();
            KeyStore keyStore = KeyStore.getInstance(this.signer.param.getKeyStoreType().name());
            keyStore.load(new FileInputStream(this.signer.param.getCertificate()), passwordCharArray);
            String alias = keyStore.aliases().nextElement();
            Certificate[] certificateChain = keyStore.getCertificateChain(alias);
            CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
            ContentSigner sha1Signer = new JcaContentSignerBuilder(
                    this.signer.param.getSignAlgorithm().name()
            ).setProvider(PROVIDER).build((PrivateKey) keyStore.getKey(alias, passwordCharArray));
            generator.addSignerInfoGenerator(
                    new JcaSignerInfoGeneratorBuilder(
                            new JcaDigestCalculatorProviderBuilder().setProvider(PROVIDER).build()
                    ).build(sha1Signer, (X509Certificate) certificateChain[0])
            );
            generator.addCertificates(new JcaCertStore(Arrays.asList(certificateChain)));
            return generator.generate(new CmsProcessableInputStream(content), true).getEncoded();
        }

        /**
         * cms数据
         */
        public static class CmsProcessableInputStream implements CMSTypedData {
            private final InputStream in;
            private final ASN1ObjectIdentifier contentType;

            CmsProcessableInputStream(InputStream is) {
                this(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), is);
            }

            CmsProcessableInputStream(ASN1ObjectIdentifier type, InputStream is) {
                this.contentType = type;
                this.in = is;
            }

            @Override
            public Object getContent() {
                return this.in;
            }

            @Override
            public void write(OutputStream out) throws IOException {
                IOUtils.copy(this.in, out);
                this.in.close();
            }

            @Override
            public ASN1ObjectIdentifier getContentType() {
                return this.contentType;
            }
        }
    }

    /**
     * 证书类型
     */
    public enum KeyStoreType {
        /**
         * JCEKS
         */
        JCEKS,
        /**
         * JKS
         */
        JKS,
        /**
         * PKCS12
         */
        PKCS12
    }

    /**
     * 签名算法枚举
     */
    public enum SignAlgorithm {
        /**
         * RSA
         */
        NONEwithRSA("RSA", "NONEwithRSA"),
        MD2withRSA("RSA", "MD2withRSA"),
        MD5withRSA("RSA", "MD5withRSA"),
        SHA1withRSA("RSA", "SHA1withRSA"),
        SHA256withRSA("RSA", "SHA256withRSA"),
        SHA384withRSA("RSA", "SHA384withRSA"),
        SHA512withRSA("RSA", "SHA512withRSA"),

        /**
         * DSA
         */
        NONEwithDSA("DSA", "NONEwithDSA"),
        SHA1withDSA("DSA", "SHA1withDSA"),

        /**
         * ECDSA
         */
        NONEwithECDSA("ECDSA", "NONEwithECDSA"),
        SHA1withECDSA("ECDSA", "SHA1withECDSA"),
        SHA256withECDSA("ECDSA", "SHA256withECDSA"),
        SHA384withECDSA("ECDSA", "SHA384withECDSA"),
        SHA512withECDSA("ECDSA", "SHA512withECDSA");

        /**
         * 有参构造
         * @param type 类型
         * @param name 名称
         */
        SignAlgorithm(String type, String name) {

        }
    }

    /**
     * 签名过滤器
     */
    public static class SignFilter {

        /**
         * 过滤器
         */
        public enum Filter {

            /**
             * Adobe.PPKLite
             */
            FILTER_ADOBE_PPKLITE(COSName.ADOBE_PPKLITE),
            /**
             * Entrust.PPKEF
             */
            FILTER_ENTRUST_PPKEF(COSName.ENTRUST_PPKEF),
            /**
             * CICI.SignIt
             */
            FILTER_CICI_SIGNIT(COSName.CICI_SIGNIT),
            /**
             * VeriSign.PPKVS
             */
            FILTER_VERISIGN_PPKVS(COSName.VERISIGN_PPKVS);

            /**
             * 过滤器
             */
            private final COSName filter;

            /**
             * 有参构造
             * @param filter 过滤器
             */
            Filter(COSName filter) {
                this.filter = filter;
            }

            /**
             * 获取过滤器
             * @return 返回过滤器
             */
            public COSName getFilter() {
                return filter;
            }
        }

        /**
         * 子过滤器
         */
        public enum SubFilter {

            /**
             * adbe.x509.rsa_sha1
             */
            SUBFILTER_ADBE_X509_RSA_SHA1(COSName.ADBE_X509_RSA_SHA1),
            /**
             * adbe.pkcs7.detached
             */
            SUBFILTER_ADBE_PKCS7_DETACHED(COSName.ADBE_PKCS7_DETACHED),
            /**
             * ETSI.CAdES.detached
             */
            SUBFILTER_ETSI_CADES_DETACHED(COSName.getPDFName("ETSI.CAdES.detached")),
            /**
             * adbe.pkcs7.sha1
             */
            SUBFILTER_ADBE_PKCS7_SHA1(COSName.ADBE_PKCS7_SHA1);

            /**
             * 过滤器
             */
            private final COSName filter;

            /**
             * 有参构造
             * @param filter 过滤器
             */
            SubFilter(COSName filter) {
                this.filter = filter;
            }

            /**
             * 获取过滤器
             * @return 返回过滤器
             */
            public COSName getFilter() {
                return filter;
            }
        }
    }
}
