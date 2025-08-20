#include <iostream>
#include <memory>
#include <chrono> // 添加chrono头文件
#include <vector>

using namespace  std;
template <typename T>
int add(T a,T b){
    return a+b;
}
template <typename T>
int sub(T a,T b){
    return a-b;
}
template <class T>
class tVer {
private:
    std::unique_ptr<T[]> data;
    int size;
public:
    explicit tVer(int size) : size(size) {
        data=std::make_unique<T[]>(size);
    };
    explicit tVer()         : size(0){
        data=std::make_unique<T[]>(size);
    }; //初始化构造size为0
    ~tVer() = default;

    void insert(int index,const T element) {
        if(index<=size){
            data[index]=element;
        }
    }
    void insert(const T& element){
        auto new_data = std::make_unique<T[]>(size+1);
        for(int i=0;i<size;i++){
            new_data[i]=data[i]; //从后往前复制
        }
        new_data[size]=element;
        data=std::move(new_data);
        size++;
    }


    int getSize(){
        return size;
    }
    T& getData(int index){
        if(index<size){
            return data[index];
        } else{
            throw std::out_of_range("Index out of range");
        }
    }
    T* getDataPtr(int index){
        if(index<size){
            return &data[index];
        } else{
            return nullptr;
        }
    }
};
int main() {
    tVer<float> tV1;
    tVer<float> tV2;

    tV1.insert(0.0f);
    tV1.insert(1.0f);
    tV1.insert(2.0f);
    tV1.insert(3.0f);
    tV1.insert(4.0f);

    tV2.insert(5.0f);
    tV2.insert(6.0f);
    tV2.insert(7.0f);
    tV2.insert(8.0f);
    tV2.insert(9.0f);

    tVer<float>* tV1Ptr=&tV1;
    tVer<float>* tV2Ptr=&tV2;
    tVer<tVer<float>*> tV_prt;
    tV_prt.insert(tV1Ptr);
    tV_prt.insert(tV2Ptr);
    for (int i = 0; i < tV_prt.getSize(); ++i) {
        auto tVPtr=tV_prt.getData(i);
        for (int j = 0; j < (tVPtr->getSize()); ++j) {
            std::cout<<(tVPtr->getData(j))<<std::endl;
        }
    }




}