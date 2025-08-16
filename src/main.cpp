#include <iostream>
#include <chrono> // 添加chrono头文件

volatile  int sum=0;

int main() {
    auto start = std::chrono::high_resolution_clock::now(); // 记录开始时间
    for(int i=0;i<1024*1024*1000;i++){
        sum+=1;
    }
    auto end = std::chrono::high_resolution_clock::now(); // 记录结束时间
    auto duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start); // 计算时间差
    std::cout << "sum="<<sum<<std::endl;
    std::cout << "Time taken: " << duration.count() << " microseconds" << std::endl; // 输出时间差
    return 0;
}