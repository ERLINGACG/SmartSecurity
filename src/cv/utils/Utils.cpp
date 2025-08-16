#include "smartsecurity/cv/utils/utils.h"



void cv_utils::ImageUtils::resizeWithPadding(
                unsigned char* input, 
                int inputSize,
                int targetWidth, int targetHeight,
                data::ImageData&  outPutData)
{
     std::vector<unsigned char> buf(input, input + inputSize); //构造临时缓冲区
     cv::Mat inputImg = cv::imdecode(buf, cv::IMREAD_COLOR);  //解码为Mat
     cv::Mat output;
     double scale = std::min(static_cast<double>(targetWidth)     / inputImg.cols, 
                             static_cast<double>(targetHeight)   / inputImg.rows);
    cv::Size scaledSize(
            static_cast<int>(std::round(inputImg.cols * scale)),  // 添加显式转换和四舍五入
            static_cast<int>(std::round(inputImg.rows * scale))   // 避免浮点精度损失
    );
        // 使用线性插值进行缩放（适合放大操作）
        cv::resize(inputImg, output, scaledSize, 0, 0, cv::INTER_LINEAR);
        
        //计算需要添加的边框
        int padH = targetHeight - output.rows;
        int padW = targetWidth - output.cols;
        
        // 均匀分配边框到四周（处理奇偶差异）
        cv::copyMakeBorder(output, output, 
                        padH/2, padH - padH/2,  // 垂直方向（上下）
                        padW/2, padW - padW/2,  // 水平方向（左右）
                        cv::BORDER_CONSTANT, cv::Scalar(0,0,0));

       std::vector<int> params_ {cv::IMWRITE_WEBP_QUALITY, 95};
       std::vector<unsigned char> outBuf;
       cv::imencode(".jpeg", output, outBuf, params_); 

       outPutData.width = output.cols;
       outPutData.height = output.rows;
       outPutData.channels = 3;
       outPutData.size = static_cast<int>(outBuf.size());
       outPutData.data = std::make_unique<unsigned char[]>(
       outPutData.width * outPutData.height * outPutData.channels);
       memcpy(outPutData.data.get(), outBuf.data(), outPutData.size);
}
void cv_utils::ImageUtils::resizeWithPadding_mat(
    cv::Mat& input,
    cv::Mat& output,
    int targetWidth, 
    int targetHeight
){
         double scale = std::min(static_cast<double>(targetWidth)/ input.cols, 
                                static_cast<double>(targetHeight)/ input.rows);
         cv::Size scaledSize(
            static_cast<int>(std::round(input.cols * scale)),  // 添加显式转换和四舍五入
            static_cast<int>(std::round(input.rows * scale))   // 避免浮点精度损失
        );
          // 缺少缩放操作（关键遗漏）
        cv::resize(input, output, scaledSize, 0, 0, cv::INTER_LINEAR);

        int padH = targetHeight - output.rows;
        int padW = targetWidth - output.cols;

         cv::copyMakeBorder(output, output, 
                        padH/2, padH - padH/2,  // 垂直方向（上下）
                        padW/2, padW - padW/2,  // 水平方向（左右）
                        cv::BORDER_CONSTANT, cv::Scalar(0,0,0));
}


